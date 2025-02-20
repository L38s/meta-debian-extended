# base recipe : meta/recipes-connectivity/bluez5/bluez5_5.50.bb
# base branch : warrior
# base commit : 1ddec43ce1cbab1984cb6925be34a05b4ccee9e0

require ${COREBASE}/meta/recipes-connectivity/bluez5/bluez5.inc

BPN = "bluez"

inherit debian-package
require recipes-debian/sources/${BPN}.inc

FILESPATH_append = ":${COREBASE}/meta/recipes-connectivity/bluez5/bluez5"
SRC_URI += "\
    file://out-of-tree.patch \
    file://init \
    file://run-ptest \
    file://0001-tests-add-a-target-for-building-tests-without-runnin.patch \
    file://0001-test-gatt-Fix-hung-issue.patch \
    file://0001-Makefile.am-Fix-a-race-issue-for-tools.patch \
    file://CVE-2018-10910.patch \
    file://tools_Fix_build_after_y2038_changes_in_glibc.patch \
"

REQUIRED_DISTRO_FEATURES = "bluez5"

# noinst programs in Makefile.tools that are conditional on READLINE
# support
NOINST_TOOLS_READLINE ?= " \
    ${@bb.utils.contains('PACKAGECONFIG', 'deprecated', 'attrib/gatttool', '', d)} \
    tools/obex-client-tool \
    tools/obex-server-tool \
    tools/bluetooth-player \
    tools/obexctl \
    tools/btmgmt \
"

# noinst programs in Makefile.tools that are conditional on TESTING
# support
NOINST_TOOLS_TESTING ?= " \
    emulator/btvirt \
    emulator/b1ee \
    emulator/hfp \
    peripheral/btsensor \
    tools/3dsp \
    tools/mgmt-tester \
    tools/gap-tester \
    tools/l2cap-tester \
    tools/sco-tester \
    tools/smp-tester \
    tools/hci-tester \
    tools/rfcomm-tester \
    tools/bnep-tester \
    tools/userchan-tester \
"

# noinst programs in Makefile.tools that are conditional on TOOLS
# support
NOINST_TOOLS_BT ?= " \
    tools/bdaddr \
    tools/avinfo \
    tools/avtest \
    tools/scotest \
    tools/amptest \
    tools/hwdb \
    tools/hcieventmask \
    tools/hcisecfilter \
    tools/btinfo \
    tools/btsnoop \
    tools/btproxy \
    tools/btiotest \
    tools/bneptest \
    tools/mcaptest \
    tools/cltest \
    tools/oobtest \
    tools/advtest \
    tools/seq2bseq \
    tools/nokfw \
    tools/create-image \
    tools/eddystone \
    tools/ibeacon \
    tools/btgatt-client \
    tools/btgatt-server \
    tools/test-runner \
    tools/check-selftest \
    tools/gatt-service \
    profiles/iap/iapd \
    ${@bb.utils.contains('PACKAGECONFIG', 'btpclient', 'tools/btpclient', '', d)} \
"
